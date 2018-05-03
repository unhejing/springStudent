package com.biz.lesson.dao.user;

import com.biz.lesson.model.user.MainMenu;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class MainMenuRepositoryTest {

    @Autowired
    private MainMenuRepository mainMenuRepository;

    @Test
    public void findByOrderByCodeAscNameAscTest() throws Exception {
        List<MainMenu> mainMenus =  mainMenuRepository.findByOrderByCodeAscNameAsc();
        Assert.assertNotNull(mainMenus);
    }

}