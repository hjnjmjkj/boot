package com.hk.ssm4.arithmetic;

import java.nio.charset.Charset;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloemFilter {
	public static void main(String[] args) {
        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        b.put("121");
        b.put("122");
        b.put("123");
        System.out.println(b.mightContain("12321"));
        BloomFilter<String> b1 = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        b1.put("aba");
        b1.put("abb");
        b1.put("abc");
        b1.putAll(b);
        System.out.println(b1.mightContain("123"));
    }
}
