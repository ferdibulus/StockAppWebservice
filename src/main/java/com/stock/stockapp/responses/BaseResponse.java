package com.stock.stockapp.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Column;
import java.util.Date;

@ResponseBody
@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {
    private String status;
    private String message;
    private Date date = new Date();
}
