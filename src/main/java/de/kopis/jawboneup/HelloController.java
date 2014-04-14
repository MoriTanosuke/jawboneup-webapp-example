package de.kopis.jawboneup;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    OAuthService service = new ServiceBuilder()
            .debug()
            .provider(JawboneUpApi.class)
            .apiKey("xv9rJySTcYg") // this is the "client_id" of your app
            .apiSecret("da1d589509e17d2aacb61195508d216e") // this is your app secret
            .scope("extended_read")
                    //TODO remove hardcoded url
            .callback("https://127.0.0.1:8080/callback")
            .build();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "redirect:" + service.getAuthorizationUrl(null);
    }


    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam String code) {
        //TODO this is getting a 'BAD REQUEST' at the moment
        Token token = service.getAccessToken(null, new Verifier(code));
        System.out.println("token = " + token);
        return "callback";
    }

}
