package com.books.management.booksmanagement.unittests.controllers;

import com.books.management.booksmanagement.controllers.BooksController;
import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.services.BooksService;
import com.books.management.booksmanagement.services.ShortlistedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.of;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksService booksService;

    @MockBean
    private ShortlistedService shortlistedService;

    @Test
    public void getAllBooksTest() throws Exception {
        when(booksService.getAllBooks()).thenReturn(getListOfBooks());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getBooks")
                .accept(MediaType.APPLICATION_JSON);

        //Get the result back
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "{\n" +
                        "bookId: 12345,\n" +
                        "titleId: 1,\n" +
                        "authorId: 1,\n" +
                        "publisherId: 1,\n" +
                        "description: \"How to be happy\",\n" +
                        "price: 123.23\n" +
                        "},\n" +
                        "{\n" +
                        "bookId: 12346,\n" +
                        "titleId: 2,\n" +
                        "authorId: 2,\n" +
                        "publisherId: 2,\n" +
                        "description: \"How to be sad\",\n" +
                        "price: 234.23\n" +
                        "}\n" +
                        "]"))
                .andReturn();
    }

    @Test
    public void getBookByIdTest() throws Exception {
        when(booksService.getBookById(12345L)).thenReturn(of(new Books(12345L, 1L, 1L
                , 1L
                , "How to be happy"
                , 123.23)));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getBook/{bookId}", "12345")
                .accept(MediaType.APPLICATION_JSON);

        //Get the result back
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                        "bookId: 12345,\n" +
                        "titleId: 1,\n" +
                        "authorId: 1,\n" +
                        "publisherId: 1,\n" +
                        "description: \"How to be happy\",\n" +
                        "price: 123.23\n" +
                        "}"))
                .andReturn();
    }

    private List<Books> getListOfBooks() {
        List<Books> list = new ArrayList<>();
        Books firstBook = new Books(12345L, 1L, 1L, 1L, "How to be happy"
                ,123.23);
        Books secondBook = new Books(12346L, 2L, 2L, 2L, "How to be sad"
                ,234.23);
        list.add(firstBook);
        list.add(secondBook);
        return list;
    }
}
