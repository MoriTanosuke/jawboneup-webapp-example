package de.kopis.jawboneup;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

public class JawboneUpApi extends DefaultApi20 {
    private static final String AUTHORIZE_URL = "https://jawbone.com/auth/oauth2/auth?response_type=code&client_id=%s&redirect_uri=%s";
    private static final String BASE_URL = "https://jawbone.com/nudge/api/v.1.1";
    private static final String ACCESS_TOKEN_URL = "https://jawbone.com/auth/oauth2/token";

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback.");
        return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
    }
}