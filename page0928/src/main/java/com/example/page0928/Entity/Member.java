package com.example.page0928.Entity;


import javax.persistence.*;

@Entity
    public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int num;

        @Column
        private String name;

        @Column
        private String id;

        @Column
        private String phone;

        @Column
        private int age;

        // Getters and Setters
        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        // Constructor
        public Member() { }

        public Member(int num, String name, String id, String phone, int age) {
            super();
            this.num = num;
            this.name = name;
            this.id = id;
            this.phone = phone;
            this.age = age;
        }

        // toString()
        @Override
        public String toString() {
            return "Member [num=" + num + ", name=" + name + ", id=" + id + ", phone=" + phone + "]";
        }

    }


