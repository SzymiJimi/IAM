package com.inzynieria.insurance;

import com.inzynieria.insurance.controller.HomeController;
import org.junit.Test;


import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class MappingTest {

    @Test
    public void testHomePage() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("index"));

    }

    @Test
    public void testHomelogin() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/login")).andExpect(view().name("login/login"));
    }
    @Test
    public void testHomefind() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/find/findClient")).andExpect(view().name("find/findClient"));
    }

    @Test
    public void testHomeContract() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/new/contract")).andExpect(view().name("contract/new"));
    }

    @Test
    public void testHomeClientData() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/client/clientData")).andExpect(view().name("client/clientData"));
    }

    /**
     *
     * @throws Exception
     */


    @Test
    public void testHomeUser() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/find/findUser")).andExpect(view().name("find/findUser"));
    }

    @Test
    public void testRegister() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/client/homeReserve")).andExpect(view().name("client/homeReserve"));
    }

    @Test
    public void testOfferadd() throws Exception{
        HomeController controller = new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/new/offer")).andExpect(view().name("offer/offerAdd"));
    }
    @Test
    public void testFindOffer() throws Exception{
        HomeController controller = new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/find/findOffer")).andExpect(view().name("find/findOffer"));
    }

    @Test
    public void testNotyficationAdd() throws Exception{
        HomeController controller = new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/new/notification")).andExpect(view().name("notification/new"));
    }

    @Test
    public void testNotyficationShow() throws Exception{
        HomeController controller = new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/show/notifications")).andExpect(view().name("notification/showList"));
    }








}
