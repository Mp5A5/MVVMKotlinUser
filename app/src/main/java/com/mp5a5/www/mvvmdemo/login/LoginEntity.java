package com.mp5a5.www.mvvmdemo.login;

/**
 * @author ：mp5a5 on 2019/4/16 14：40
 * @describe
 * @email：wwb199055@126.com
 */
public class LoginEntity extends BaseMidEntity<LoginEntity> {

  public ResultEntity result;

  public static class ResultEntity {
    public String province;
    public String city;
    public String areacode;
    public String zip;
    public String company;
    public String card;
  }

}
