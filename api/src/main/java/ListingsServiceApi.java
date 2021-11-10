import beans.ListingsBean;
import classes.Listing;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("listings")
public class ListingsServiceApi {
    private Logger log = Logger.getLogger(ListingsServiceApi.class.getName());

    @Inject
    private ListingsBean listingsBean;

    @GET
    public Response getAllListings() {
        List<Listing> listing = listingsBean.getListings();
        return Response.status(Response.Status.OK).entity(listing).build();
    }


    @POST
    public Response createListing(Listing listing) {
        if(listing.getTitle() == null || listing.getDescription() == null || listing.getType() == null ||
                listing.getMonthlyPrice() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            listing = listingsBean.createListing(listing);
        }

        return Response.status(Response.Status.CONFLICT).entity(listing).build();
    }
}
