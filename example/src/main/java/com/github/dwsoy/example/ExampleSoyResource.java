package com.github.dwsoy.example;

import com.github.dwsoy.view.SoyView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class ExampleSoyResource {

    @GET
    public SoyView<ExampleBean> index() {
        return new SoyView<ExampleBean>("example.sample", new ExampleBean("foo"));
    }
}
