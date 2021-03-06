package edu.harvard.we99.security;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Service for creating new user accounts. The workflow for new user accounts is
 * as follows:
 *
 * - user visits the create account page and enters their name and email
 * - system inserts a User entity w/ a UUID for a password
 * - system sends the user an email welcoming them and providing a link to click on
 * - user visits the page which makes a GET call w/ the given uuid embedded in
 *   the link to verify that account registration is pending.
 * - user enters a password to use
 * - user submits the form and the system confirms the submitted email/uuid
 *   combo and updates the User entity password (after salting and hashing it)
 * - user gets a sucess page and is asked to log in using their email and newly
 *   entered password
 *
 * This service is designed to offer a basic/no-frills user registration model.
 * Additional features are out of scope. The final product version of this app
 * should incorporate something more capable like OpenAM in order to have SSO
 * and other features.
 *
 * @author mford
 */
@Path("/createAccount")
@Api(value = "/createAccount",
        description = "Service for creating new user accounts")
@Consumes(MediaType.APPLICATION_JSON)
public interface CreateAccountService {
    /**
     * A self service for users to create their own account.
     *
     * This is a temporary measure. The core capabilities of the system do not
     * attempt to provide a full identity management suite. It's likely that this
     * service would be swapped out for something else like a SSO and/or central
     * identity management service. Perhaps OpenAM?
     * @param user
     * @param request
     * @return 307 if the tmp account can be created as you'll be redirected to
     *          the registration success page, 409 if the email's already taken
     * @statuscode 307 on success to direct the user to the 'check your email' page
     * @statuscode 404 if there is no user with this email
     */
    @POST
    @ApiOperation("A self service for users to create their own account.")
    Response createAccount(User user, @Context HttpServletRequest request);


    /**
     * Fetches the user associated with this registration key or returns an error
     * if there is no account with this key that needs activating.
     * @param uuid
     * @param user
     * @return the user to populate the form to set their password or a 404 if there
     *         is no user account awaiting activation.
     * @statuscode 404 if there is no user with this email / uuid
     */
    @Path("/verify/{uuid}")
    @POST
    @ApiOperation("Fetches the user associated with this registration key")
    User verifyAccount(@PathParam("uuid") String uuid, User user);

    /**
     * Accepts a password for the given user account. Verifies that the UUID matches
     * the current password for the user.
     *
     * After receiving a 200 from this call, the user should be able to login with
     * their email and password.
     * @param user - with a password value that we'll SHA-256 hash (with user specific salt)
     *                 for their password
     * @return 200 to indicate success, 404 if the account was already activated.
     *         possibly other codes in the future if password strength rules are applied
     * @statuscode 404 if there is no user with this email / uuid
     * @statuscode 200 if successful
     */
    @Path("/activate/{uuid}")
    @POST
    @ApiOperation("Accepts a password for the given user account.")
    Response activateAccount(@PathParam("uuid") String uuid, User user);

}
