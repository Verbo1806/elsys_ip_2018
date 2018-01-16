package org.elsys.ip.rest.resource;

import org.elsys.ip.rest.model.MatchInfo;
import org.elsys.ip.rest.service.MatchService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.util.HashSet;
import java.util.List;

@Path("match")
public class MatchResource {

  private MatchService matchService = new MatchService();

  @GET
  @Path("all")
  @Produces("application/json")
  public List<MatchInfo> getTestList() {
    List<MatchInfo> matchInfoList = matchService.getTestList();
    return matchInfoList;
  }

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public MatchInfo getTest(@PathParam("id") Integer id) {
    return matchService.getTestById(id);
  }


  @GET
  @Produces("application/json")
  public List<MatchInfo> getByMultipleIds(@QueryParam("id") final List<Integer> listOfIds) {
    return matchService.getByMultipleIds(new HashSet<>(listOfIds));
  }

  @GET
  @Produces("application/json")
  @Path("/byFields/")
  public List<MatchInfo> getByMultipleFields(
          @Context UriInfo uriInfo
  ) {
    MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    return matchService.getByMultipleFields(params);
  }

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  @Path("/multiple/")
  public List<MatchInfo> bulkCreateMatches(List<MatchInfo> matches) {

    matchService.addList(matches);
    return matches;
  }



  @GET
  @Path("/download")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response downloadTest() throws Exception {
    matchService.writeCSVFile("",null);
    File file = new File("C:\\data.csv");
    return Response
      .ok(file)
      .header("Content-Disposition", "attachment; filename=" + file.getName())
      .build();
  }


  @POST
  @Path("/upload")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response uploadTest() throws Exception {
    matchService.readCSVFile("",null);
    File file = new File("C:\\data.csv");
    return Response
            .ok(file)
            .header("Content-Disposition", "attachment; filename=" + file.getName())
            .build();
  }


  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public MatchInfo saveTest(MatchInfo test) {
    return matchService.saveTest(test);
  }


  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  public MatchInfo updateTest(@PathParam("id") Integer id, MatchInfo test) {
    return matchService.updateTest(id, test);
  }


  @DELETE
  @Path("/{id}")
  public void deleteTest(@PathParam("id") Integer id) {
    matchService.deleteTest(id);
  }

}
