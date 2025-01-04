package com.example.coinchangewizard.resource;

import com.example.coinchangewizard.core.CoinChangeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

//request body
class CoinChangeRequest {
    public BigDecimal targetAmount;
    public List<BigDecimal> coinDenominations;
}

// respond body
class CoinChangeResponse {
    public List<BigDecimal> coins;
    public CoinChangeResponse(List<BigDecimal> coins) {
        this.coins = coins;
    }
}

@Path("/coinchange")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoinChangeResource {

    private final CoinChangeService service;

    public CoinChangeResource(CoinChangeService service) {
        this.service = service;
    }

    @POST
    public Response getMinimumCoins(CoinChangeRequest request) {
        try {
            List<BigDecimal> result = service.calculateMinimumCoins(
                    request.targetAmount,
                    request.coinDenominations
            );
            return Response.ok(new CoinChangeResponse(result)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}