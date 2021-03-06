package edu.harvard.we99.domain.results.analysis;

import edu.harvard.we99.domain.Coordinate;
import edu.harvard.we99.domain.Dose;
import edu.harvard.we99.domain.ExperimentPoint;
import edu.harvard.we99.domain.results.DoseResponseResult;
import edu.harvard.we99.domain.results.WellResults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/**
 * @author alan orcharton
 */
public class ExperimentPointsFunction implements Function<Map<Long,List<WellResults>>, List<ExperimentPoint>> {

    private final DoseResponseResult doseResponse;
    private final Set<Long> plateIds;



    public  ExperimentPointsFunction(DoseResponseResult doseResponse,Set<Long> plateIds ){
            this.doseResponse = doseResponse;
            this.plateIds = plateIds;

    }

    @Override
    public List<ExperimentPoint> apply(Map<Long, List<WellResults>> longWellResultsMap){

        List<ExperimentPoint> newpoints = new ArrayList<>();
        for(Long id : plateIds) {
//            Map<Coordinate, List<Sample>> cord = new HashMap<>();    //stores samples results by coordinate
            Map<Coordinate, WellResults> cordToResult = new HashMap<>();
            List<WellResults> plateResults = longWellResultsMap.get(id);   //gets list well results for a plate
//            List<WellResults> includedResults = plateResults.stream()
//                                .filter(wresult -> wresult.getResultStatus() == ResultStatus.INCLUDED)
//                                .collect(Collectors.toList());
            plateResults.forEach(wr -> cordToResult.put(wr.getCoordinate(), wr));   //map Result to co-ordinate


            List<Dose> epdoses = doseResponse.getDoses();         // Gets the Doses for the DR
            epdoses.stream()
                    .filter(d -> Objects.equals(d.getPlateId(), id))
                    .forEach(d -> {
                ExperimentPoint npoint = new ExperimentPoint(id, d.getId());
                npoint.setX(d.getAmount().getNumber());
                npoint.setLogx(Math.log10(d.getAmount().getNumber()));
                cordToResult.keySet().forEach(wellcoord -> {
                    if (wellcoord.equals(d.getWell().getCoordinate())) {
                        npoint.setY(cordToResult.get(wellcoord).getSamples().get(0).getNormalized());
                        npoint.setResultStatus(cordToResult.get(wellcoord).getResultStatus());
                    }
                });
                newpoints.add(npoint);
            });


        }

        return newpoints;

    }


//    public List<ExperimentPoint> apply2(Map<Long, List<WellResults>> longWellResultsMap) {
//
//        List<ExperimentPoint> newpoints = new ArrayList<>();
//        for(Long id : plateIds){
//            Map<Coordinate, List<Sample>> cord = new HashMap<>();    //stores samples results by coordinate
//            List<WellResults> plateResults = longWellResultsMap.get(id);   //gets list well results for a plate
//            plateResults.forEach(wr -> cord.put(wr.getCoordinate(), wr.getSamples()));   //map sample to co-ordinate
//            List<Dose> epdoses = doseResponse.getDoses();         // Gets the Doses for the DR
//            Map<Long, Dose> epdosesmap = new HashMap<>();         // store dose per dose id
//            epdoses.forEach(d -> epdosesmap.put(d.getId(),d));
//            List<ExperimentPoint> dosepoints = doseResponse.getExperimentPoints();   //gets all the Experiment points
//            for( ExperimentPoint ep : dosepoints){
//                if(ep.getPlateId() == id) {
//                    ExperimentPoint npoint = new ExperimentPoint(id, ep.getDoseId());
//                    npoint.setX(epdosesmap.get(ep.getDoseId()).getAmount().getNumber());
//                    cord.keySet().forEach(wellcoord -> {
//                        if (wellcoord.equals(epdosesmap.get(ep.getDoseId()).getWell().getCoordinate())) {
//                            npoint.setY(cord.get(wellcoord).get(0).getNormalized());      //protectet against no well at co-ord
//                        }
//                    });
//                    newpoints.add(npoint);
//                }
//            }
//
//        }
//
//        return newpoints;
//    }
}
