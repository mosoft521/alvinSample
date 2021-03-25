package com.gmail.mosoft521.interview.date20210325;

public class Run {
    static class Animal {
        public Animal() {
            System.out.println("Animal init");
        }

        {
            System.out.println("I'm an animal");
        }

        static {
            System.out.println("static Animal");
        }
    }

    static class Rabbit extends Animal {
        public Rabbit() {
            System.out.println("Rabbit init");
        }

        {
            System.out.println("I'm a Rabbit");
        }

        static {
            System.out.println("static Rabbit");
        }
    }

    public static void main(String[] args) {
        new Rabbit();
    }
}
/*
static Animal
static Rabbit
I'm an animal
Animal init
I'm a Rabbit
Rabbit init
 */