package com.fran3r.boundary;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @author Fran Alonso @ byteflair.com
 */
//Utility class, I add the private constructor to hidden the default
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceUtils {
    private static final Pattern REL_PATTERN = Pattern.compile("^(.*)/(.*)$");

    public static String getIdFromRel(ResourceSupport resource, String rel) {
        String id = null;

        if (resource.getLinks() != null && resource.getLink(rel) != null) {
            Matcher matcher = REL_PATTERN.matcher(resource.getLink(rel).getHref());
            if (matcher.matches()) {
                id = matcher.group(2);
            }
        }

        return id;
    }

    public static List<String> getIdsFromRel(ResourceSupport resource, String rel) {
        return ResourceUtils.getLinks(resource, rel).stream().map(link->{
            String id = null;
            Matcher matcher = REL_PATTERN.matcher(link.getHref());
            if (matcher.matches()) {
                id = matcher.group(2);
            }
            return id;
        }).collect(Collectors.toList());
    }

    public static List<Link> getLinks(ResourceSupport resource, String rel) {
        return resource.getLinks().stream().filter(link->link.getRel().equals(rel)).collect(Collectors.toList());
    }
}
