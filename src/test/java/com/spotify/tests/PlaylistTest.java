package com.spotify.tests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.spotify.api.StatusCode;
import com.spotify.api.PlaylistApi.PlaylistAPI;
import com.spotify.pojo.Playlist;
import com.spotify.utils.ConfigLoader;
import com.spotify.utils.FakerUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;


@Epic("Test epic123 =  user should able to work with adding and removing songs")
@Feature("Spotify = Playlist addition features")
public class PlaylistTest {
	
	Playlist playlist;
	Playlist requestpayload;
	
	
	
    @Link("https://example.org")       //-> for providing any additional links
    @Link(name = "allure", type = "mylink") 
    @TmsLink("12345")                            // manual test case link
    @Issue("1234567")                            // defect link
	@Story("Test Story -123: playlist creation story")
	@Test(priority = 1, description = "create playlist")
	@Description("this test will create a playlist")
	public void create_playlist() throws Exception
	{
    	Playlist requestpayload = Playlist.builder()
				                  .name(FakerUtils.generateRandomPlaylistName())
				                  .description(FakerUtils.generateRandomDescription())
				                  ._public(false).build();
		Response post = PlaylistAPI.Post(requestpayload, ConfigLoader.getObject().getUser());
		
		playlist = post.as(Playlist.class);
		assertThat(playlist.getName(),equalTo(requestpayload.getName()));
		assertThat(playlist.getDescription(),equalTo(requestpayload.getDescription()));
		assertThat(playlist.get_public(),equalTo(requestpayload.get_public()));
	}

	
	@Story("Test Story -123: playlist creation story")
	@Test(priority = 2, description = "get playlist details")
	@Description("this test will get a playlist")
	public void get_created_playlist() throws Exception
	{
		Response post = PlaylistAPI.get(playlist.getId());
		playlist = post.as(Playlist.class);
		assertThat(playlist.getName(),equalTo(requestpayload.getName()));
		assertThat(playlist.getDescription(),equalTo(requestpayload.getDescription()));
	}
	
	
	@Story("Test Story -123: playlist creation story")
	@Test(priority = 3, description = "update the playlist details")
	@Description("this test will update a existing playlist playlist")
	public void update_paylist() throws Exception
	{
		Playlist requestpayload = Playlist.builder()
				                  .name(FakerUtils.generateRandomPlaylistName())
				                  .description(FakerUtils.generateRandomDescription())
				                  ._public(false).build();
		Response post = PlaylistAPI.put(requestpayload, playlist.getId());
	}
	
	
	//negative request
	@Story("Test Story -123: playlist creation story")
    @Test(priority = 4, description = "test invalid token")
    @Description("this test will test use case of a invalid token")
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() throws Exception{
        String invalid_token = "12345";
        Playlist requestPlaylist =  Playlist.builder()
                .name(FakerUtils.generateRandomPlaylistName())
                .description(FakerUtils.generateRandomDescription())
                ._public(false).build();
        Response response = PlaylistAPI.PostInvalidToken(requestPlaylist, invalid_token, ConfigLoader.getObject().getUser());
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
    }
    
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.getCode()));
    } 
    
    
    @BeforeMethod
    public void beforeEach(Method m)  // method from reflection api of java
    {
    	System.out.println("starting the test for : "+ m.getName());
    	System.out.println("Thread id: "+ Thread.currentThread().getId());
    }
    
    @AfterMethod
    public void afterEach(Method m)
    {
    	System.out.println("end the test for : "+ m.getName());
    	System.out.println("---------------------------end-----------------------------------");
    }
}
