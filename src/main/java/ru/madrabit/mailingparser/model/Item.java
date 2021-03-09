package ru.madrabit.mailingparser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Item {
    private String name;
    private String date;
    private List<Integer> departments;
}
