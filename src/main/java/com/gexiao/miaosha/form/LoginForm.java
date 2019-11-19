package com.gexiao.miaosha.form;

import com.gexiao.miaosha.constraints.ListNotNull;
import com.gexiao.miaosha.constraints.Phone;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/19 11:07
 */
@Data
public class LoginForm {

    @NotNull(message = "名字不能为空")
    private String name;

    @Phone
    private String phone;

    @ListNotNull
    private List<String> stringList;
}
