package com.gmail.mosoft521.github;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.mosoft521.github.dto.Repository;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpCookie;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllReposWithUpstream {
    private static String URL_BASE = "https://api.github.com";
    private static String OWNER = "mosoft521";
    private static String HEADER_AUTHORIZATION = "Authorization";
    private static String HEADER_TOKEN = "token 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";
    private static String CURRENT_USER_REPOSITORIES_URL = URL_BASE + "/user/repos";
    private static String REPOSITORY_URL = URL_BASE + "/repos/${owner}/${repo}";

    /*cd fks-alvin
    echo cd fks-alvin
    git remote add upstream git@github.com:JacksonTian/fks.git
    echo git remote add upstream
    cd ..
    echo cd ..*/

    public static void main(String[] args) {
        FileReader fileReader = new FileReader("E:\\GIT\\repos.txt");
        FileWriter fileWriter = new FileWriter("E:\\GIT\\init-2-java.sh");
        //文件头
        fileWriter.write("#! /bin/bash\n");
        fileWriter.append("#初始化所有项目的上游库\n");

        List<String> repos = fileReader.readLines();
        for (String repo : repos) {
            String REPOSITORY_URL_TEMP = URL_BASE + "/repos/" + OWNER + "/" + repo;
            String parentURL = getRepoParentUrl(REPOSITORY_URL_TEMP);
            if (StringUtils.isNotBlank(parentURL)) {
                fileWriter.append("cd " + repo + "-alvin\n");
                fileWriter.append("echo cd " + repo + "-alvin\n");
                fileWriter.append("git remote add upstream " + parentURL + "\n");
                fileWriter.append("echo git remote add upstream " + parentURL + "\n");
                fileWriter.append("cd ..\n");
                fileWriter.append("echo cd ..\n");
            }
        }
    }

    private static String getRepoParentUrl(String repo) {
        HttpRequest get = HttpUtil.createGet(repo)
                .header(HEADER_AUTHORIZATION, HEADER_TOKEN);
        Console.log(get.getUrl());
        HttpResponse httpResponse = get.execute();
        Console.log(httpResponse.body());
        String jsonData = httpResponse.body();
        System.out.println(jsonData);
        Repository repository = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//只要非空字段
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//json转bean忽略没有的字段
            repository = mapper.readValue(jsonData, Repository.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (null != repository) {
            if (null != repository.getParent()) {
                return repository.getParent().getSsh_url();
            }
        }
        return null;
    }

    private static List<String> getRepos() {
        HttpRequest get = HttpUtil.createGet(CURRENT_USER_REPOSITORIES_URL)
                .header(HEADER_AUTHORIZATION, HEADER_TOKEN);
        Console.log(get.getUrl());
        HttpResponse httpResponse = get.execute();
        Console.log(httpResponse.body());
        String jsonData = httpResponse.body();
        System.out.println(jsonData);
        List<Repository> repos = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//只要非空字段
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//json转bean忽略没有的字段
            repos = mapper.readValue(jsonData, new TypeReference<List<Repository>>() {
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<String> repoList = new ArrayList<>();
        for (Repository repo : repos) {
            System.out.println(repo.getUrl());
            repoList.add(repo.getUrl());
        }
        return repoList;
    }
}
