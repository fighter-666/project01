package com.example.java.procy;

/**
 * 定义一个代表诉讼的接口（抽象主题Subject）
 */
public interface ILawSuit {
    void submit(String proof);

    void defend();
}
