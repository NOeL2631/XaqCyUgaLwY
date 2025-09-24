// 代码生成时间: 2025-09-24 12:38:25
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * ManagedBean used to validate URL links.
 */
# 添加错误处理
@ManagedBean
@RequestScoped
public class UrlValidatorService {

    // Regular expression for validating a URL
    private static final String URL_REGEX = "^(https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
# NOTE: 重要实现细节
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public boolean validateUrl(String url) {
        // Check if the URL is null or empty
        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        // Validate URL using regular expression
        Matcher matcher = URL_PATTERN.matcher(url);
        if (!matcher.matches()) {
            return false;
        }

        // Try to create a URL object to further validate
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Adds a FacesMessage if the URL is invalid.
# NOTE: 重要实现细节
     *
# 改进用户体验
     * @param url The URL to be validated.
     */
    public void validateUrlWithMessage(String url) {
        if (!validateUrl(url)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
# 增强安全性
                "Invalid URL!", "Please enter a valid URL."));
# 改进用户体验
        }
    }
}
