package edu.harvard.we99.services.experiments;

import edu.harvard.we99.domain.lists.Users;
import edu.harvard.we99.services.storage.ExperimentStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Generated;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author mford
 */
public class MemberResourceImpl implements MemberResource {

    private static final Logger log = LoggerFactory.getLogger(MemberResourceImpl.class);

    private Long id;
    private final ExperimentStorage storage;

    public MemberResourceImpl(ExperimentStorage storage) {
        this.storage = storage;
    }

    @Override
    public Users listMembers() {
        try {
            return storage.listMembers(id);
        } catch(Exception e) {
            log.error("error listing memebers for experiment {}", id, e);
            throw new WebApplicationException(Response.serverError().build());
        }
    }

    @Override
    public Response setMembers(List<Long> userIds) {
        try {
            storage.addMembers(id, userIds);
            return Response.ok().build();
        } catch(Exception e) {
            log.error("error updating memebers for experiment {} userId {}", id, userIds, e);
            throw new WebApplicationException(Response.serverError().build());
        }
    }

    @Override
    public Response addMember(Long userId) {
        try {
            storage.addMember(id, userId);
            return Response.ok().build();
        } catch (Exception e) {
            log.error("error adding memeber for experiment {} userId {}", id, userId, e);
            throw new WebApplicationException(Response.serverError().build());
        }
    }

    @Override
    public Response removeMember(Long userId) {
        try {
            storage.removeMember(id, userId);
            return Response.ok().build();
        } catch (Exception e) {
            log.error("error removing memeber from experiment {} userId {}", id, userId, e);
            throw new WebApplicationException(Response.serverError().build());
        }
    }

    @Generated(value = "generated by IDE")
    public Long getId() {
        return id;
    }

    @Generated(value = "generated by IDE")
    public void setId(Long id) {
        this.id = id;
    }
}
