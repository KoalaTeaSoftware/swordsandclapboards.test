package testFramework.helpers;

import testFramework.Context;
import testFramework.actors.Actor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class resourceLocator {

    /**
     * The Java standard URL convertor, doesn't really help. If you miss the host, then it regards the first element of
     * the path as the host. This lets you omit the scheme, or the host. It uses the values set in the SUT configuration
     * file.
     *
     * @param in - URL, that can have scheme and / or host missing
     * @return - if scheme or host are missing, then these are supplied using the defaults. Null => can't make a good
     * url
     */
    public static String interpretURL(String in) {
        String result = "";
        Matcher matcher;
        final String regex = "^(https|http)?(://)?((?!-)[A-Za-z0-9-.]{1,63}(?<!-)\\.+[A-Za-z]{2,6})?(.*)";
        final Pattern REGEX = Pattern.compile(regex);
        matcher = REGEX.matcher(in);
        if (matcher.find()) {
            try {
                result += matcher.group(1) == null ? Context.sutConfiguration.getProperty("defaultScheme") : matcher.group(1) + "://";
                result += matcher.group(3) == null ? Context.sutConfiguration.getProperty("defaultHost") : matcher.group(3);
                if (matcher.group(4) == null)
                    result += "/";
                else if (matcher.group(4).length() == 0)
                    result += "/";
                else
                    result += matcher.group(4).charAt(0) == '/' ? matcher.group(4) : '/' + matcher.group(4);
                return result;
            } catch (NoSuchFieldException e) {
                Actor.writeToHtmlReport("Unable to supply a default scheme, or host");
            }
        }
        // if we have got here, there has been a problem (e.g. unable to match, or get defaults (if needed))
        Actor.writeToHtmlReport("Unable to make a good URL out of >" + in + "<");
        Actor.writeToHtmlReport("scheme: " + matcher.group(1));
        Actor.writeToHtmlReport("scheme's magic letters: " + matcher.group(2));
        Actor.writeToHtmlReport("host: " + matcher.group(3));
        Actor.writeToHtmlReport("the rest: " + matcher.group(4));
        return null;
    }


}
