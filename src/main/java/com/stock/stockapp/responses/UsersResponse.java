package com.stock.stockapp.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Getter
@Setter
@NoArgsConstructor
public class UsersResponse extends BaseResponse{
    private String username;
    private String password;
}
