package ru.baryshev.kirill;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RemovalAlgorithms {
    LRU ("LRU","Less Recent Usage"),
    LFU ("LFU","Less Frequent Usage");

    private String id;
    private String def;
}
