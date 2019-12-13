package com.webank.webase.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Slf4j
@Data
@Service
public class TransactionService {
    @Autowired
    private RestTemplate rest;
    @Value("${transactionUrl}")
    private String url;
    @Value("${userAddress}")
    private String user;
    @Value("${groupId}")
    private int groupId;
    @Value("${useAes}")
    private Boolean useAes;
    @Value("${contract.name}")
    private String contractName;
    @Value("${contract.address}")
    private String contractAddress;
    @Value("${contract.funcName}")
    private String funcName;
    @Value("${contract.funcParam}")
    private String funcParam;
    private TransactionParam transParam;
    private GetTransactionParam getParam;
    
    public String sendPostTransaction(String funcname ,String funcparam) {

        try {
            transParam = new TransactionParam();
            transParam.setGroupId(groupId);
            transParam.setContractAddress(contractAddress);
            transParam.setUseAes(useAes);
            transParam.setUser(user);
            transParam.setContractName(contractName);
            transParam.setFuncName(funcname);
            transParam.setFuncParam(JSONArray.parseArray(funcparam));

            log.info("transaction param:{}", JSON.toJSONString(transParam));
            Object rsp = rest.postForObject(url, transParam, Object.class);
            String rspStr = "null";
            if (Objects.nonNull(rsp)) {
                rspStr = JSON.toJSONString(rsp);
                return "Successful!";
            }
            log.info("transaction result:{}", rspStr);
        } catch (Exception ex) {
            log.error("fail sendTransaction", ex);
            return "Failed!";
        }
        return null;
      // System.exit(1);
    }
    public String sendGetTransaction(String funcname ,String funcparam) {

        try {
            transParam = new TransactionParam();
            transParam.setGroupId(groupId);
            transParam.setContractAddress(contractAddress);
            transParam.setUseAes(useAes);
            transParam.setUser(user);
            transParam.setContractName(contractName);
            transParam.setFuncName(funcname);
            transParam.setFuncParam(JSONArray.parseArray(funcparam));

            log.info("transaction param:{}", JSON.toJSONString(transParam));
            String[] rsp = rest.postForObject(url, transParam, String[].class);
            String rspStr = "null";
            if (Objects.nonNull(rsp)) {
                rspStr = JSON.toJSONString(rsp);
                List<String> rsps=Arrays.asList(rsp);
                return rspStr;
            }
            log.info("transaction result:{}", rspStr);
        } catch (Exception ex) {
            log.error("fail sendTransaction", ex);
        }
        return null;
      // System.exit(1);
    }
}
