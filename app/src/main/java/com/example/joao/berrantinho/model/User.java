package com.example.joao.berrantinho.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by João Carlos on 5/9/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class User extends SugarRecord {
  @Column(name = "USER_NAME")
  private String userName;
}
