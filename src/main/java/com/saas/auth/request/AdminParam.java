package com.saas.auth.request;

import com.saas.auth.vo.Admin;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("LoginParam")
public class AdminParam extends Admin {

}
