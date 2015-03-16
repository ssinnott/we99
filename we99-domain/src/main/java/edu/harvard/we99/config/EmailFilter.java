package edu.harvard.we99.config;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filter for new user registration to restrict the process to certain email
 * addresses. The pattern could be a regex wildcard or it could restrict emails
 * to a specific domain which might be suitable for a single enterprise.
 *
 * @author mford
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailFilter implements Predicate<String> {

    @XmlTransient
    private Pattern pattern;
    private String expression = ".+";

    public EmailFilter() {
    }

    public EmailFilter(String expression) {
        compile();
        this.expression = expression;
    }

    @Generated(value = "generated by IDE")
    public String getExpression() {
        return expression;
    }

    @Generated(value = "generated by IDE")
    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean test(String s) {
        compile();
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    private void compile() {
        if (pattern == null) {
            pattern = Pattern.compile(expression);
        }
    }
}