package com.test.designPattern.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlipayTradePrecreateRequestBuilder {

    private String subject;							//订单主题
    private String TotalAmount;					    //订单总金额，单位为元
    private String OutTradeNo;						//订单号
    private String UndiscountableAmount;	        //默认0.0
    private String sellerId;						//卖家支付宝账号ID
    private String body;							//订单描述
    private String operatorId;						//商户操作员编号 13688889999
    private String storeId;							//商户门店编号
    private Map<String, String> extendParams;		//业务扩展参数
    private String timeoutExpress;				    //支付超时
    private String notifyUrl;                       //支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
    private List GoodsDetailList;                   //商品明细列表

    public String getSubject() {
        return subject;
    }

    public AlipayTradePrecreateRequestBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public AlipayTradePrecreateRequestBuilder setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
        return this;
    }

    public String getOutTradeNo() {
        return OutTradeNo;
    }

    public AlipayTradePrecreateRequestBuilder setOutTradeNo(String outTradeNo) {
        OutTradeNo = outTradeNo;
        return this;
    }

    public String getUndiscountableAmount() {
        return UndiscountableAmount;
    }

    public AlipayTradePrecreateRequestBuilder setUndiscountableAmount(String undiscountableAmount) {
        UndiscountableAmount = undiscountableAmount;
        return this;
    }

    public String getSellerId() {
        return sellerId;
    }

    public AlipayTradePrecreateRequestBuilder setSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getBody() {
        return body;
    }

    public AlipayTradePrecreateRequestBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public AlipayTradePrecreateRequestBuilder setOperatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public String getStoreId() {
        return storeId;
    }

    public AlipayTradePrecreateRequestBuilder setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public Map<String, String> getExtendParams() {
        return extendParams;
    }

    public AlipayTradePrecreateRequestBuilder setExtendParams(Map<String, String> extendParams) {
        this.extendParams = extendParams;
        return this;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public AlipayTradePrecreateRequestBuilder setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public AlipayTradePrecreateRequestBuilder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public List getGoodsDetailList() {
        return GoodsDetailList;
    }

    public AlipayTradePrecreateRequestBuilder setGoodsDetailList(List goodsDetailList) {
        GoodsDetailList = goodsDetailList;
        return this;
    }


    public static void main(String[] args) {
        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject("subject")							    //订单主题
                .setTotalAmount("totalAmount")					    //订单总金额，单位为元
                .setOutTradeNo("outTradeNo")						//订单号
                .setUndiscountableAmount("undiscountableAmount")	//默认0.0
                .setSellerId("sellerId")							//卖家支付宝账号ID
                .setBody("body")									//订单描述
                .setOperatorId("operatorId")						//商户操作员编号 13688889999
                .setStoreId("storeId")							    //商户门店编号
                .setExtendParams(new HashMap<String, String>())		//业务扩展参数
                .setTimeoutExpress("timeoutExpress")				//支付超时
                .setNotifyUrl("http://www.test-notify-url.com")     //支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(new ArrayList());			    //商品明细列表
    }
}
