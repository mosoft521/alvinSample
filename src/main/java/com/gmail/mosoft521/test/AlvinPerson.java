package com.gmail.mosoft521.test;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Data
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class AlvinPerson {
    @NonNull
    private int id;
    private String name;

    public AlvinPerson(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        AlvinPerson other = (AlvinPerson) obj;
//        if (id != other.id) {
//            return false;
//        }
//        if (name == null) {
//            if (other.name != null) {
//                return false;
//            }
//        } else if (!name.equals(other.name)) {
//            return false;
//        }
//        return true;
//    }

    public static void main(String[] args) {
        List<AlvinPerson> listA = new ArrayList<>();
        List<AlvinPerson> listB = new ArrayList<>();
        listA.add(new AlvinPerson(1, "aa"));
        listA.add(new AlvinPerson(2, "bb"));
        listA.add(new AlvinPerson(3, "cc"));
        listA.add(new AlvinPerson(4, "dd"));
        listA.add(new AlvinPerson(5, "ee"));

        listB.add(new AlvinPerson(1, "aa"));
        listB.add(new AlvinPerson(2, "bb"));
        listB.add(new AlvinPerson(4, "dd"));
        listB.add(new AlvinPerson(5, "ee"));
        listB.add(new AlvinPerson(6, "ff"));
        listB.add(new AlvinPerson(7, "gg"));
        System.out.println("==============================================原始集");
        System.out.println(listA);
        System.out.println(listB);


        //交集1-1
        List<AlvinPerson> listC = listA.stream().filter(item -> listB.contains(item)).collect(Collectors.toList());
        System.out.println("==============================================交集1-1");
        System.out.println(listA);
        System.out.println(listB);
        System.out.println(listC);

        //交集1-2
        List<AlvinPerson> listCU = listA.stream().filter(listB::contains).collect(Collectors.toList());
        System.out.println("==============================================交集1-2");
        System.out.println(listA);
        System.out.println(listB);
        System.out.println(listCU);

        //交集2-2
        List<AlvinPerson> listCU2 = listB.stream().filter(listA::contains).collect(Collectors.toList());
        System.out.println("==============================================交集2-2");
        System.out.println(listA);
        System.out.println(listB);
        System.out.println(listCU2);

//        //先合体
//        listA.addAll(listB);
//        //再去重
//        List<AlvinPerson> listC2 = listA.stream().distinct().collect(Collectors.toList());
//        System.out.println("==============================================并集");
//        System.out.println(listA);
//        System.out.println(listB);
//        System.out.println(listC2);

        List<AlvinPerson> listC3 = listA.stream().filter(item -> !listB.contains(item)).collect(Collectors.toList());
        System.out.println("==============================================差集1");
        System.out.println(listA);
        System.out.println(listB);
        System.out.println(listC3);

        List<AlvinPerson> listC4 = listB.stream().filter(item -> !listA.contains(item)).collect(Collectors.toList());
        System.out.println("==============================================差集2");
        System.out.println(listA);
        System.out.println(listB);
        System.out.println(listC4);

    }
}
