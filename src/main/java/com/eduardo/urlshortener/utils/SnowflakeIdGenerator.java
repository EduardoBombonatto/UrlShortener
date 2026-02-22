package com.eduardo.urlshortener.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator {
    // Época customizada em SEGUNDOS (Ex: 1 de Janeiro de 2024)
    private static final long CUSTOM_EPOCH = 1704067200L;

    // Configuração para 41 bits totais:
    // 31 bits para tempo (dura ~68 anos)
    // 2 bits para máquina (permite até 4 servidores rodando juntos)
    // 8 bits para sequência (permite até 256 URLs encurtadas por segundo por servidor)
    private static final long MACHINE_ID_BITS = 2L;
    private static final long SEQUENCE_BITS = 8L;

    private static final long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    private static final long MACHINE_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;

    private final long machineId;

    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(@Value("${snowflake.machine.id:1}") long machineId) {
        this.machineId = machineId;
        if (this.machineId > MAX_MACHINE_ID || this.machineId < 0) {
            throw new IllegalArgumentException("Machine ID deve ser entre 0 e " + MAX_MACHINE_ID);
        }
    }

    public synchronized long nextId() {
        long currentTimestamp = timestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Relógio recuou.");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // Se esgotar as 256 requisições num único segundo, espera o próximo segundo
                currentTimestamp = waitNextSecond(currentTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // Gera o ID compactado em no máximo 41 bits
        return ((currentTimestamp - CUSTOM_EPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (machineId << MACHINE_ID_SHIFT)
                | sequence;
    }

    // Agora trabalha em Segundos em vez de Milissegundos
    private long timestamp() {
        return System.currentTimeMillis() / 1000;
    }

    private long waitNextSecond(long currentTimestamp) {
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}
