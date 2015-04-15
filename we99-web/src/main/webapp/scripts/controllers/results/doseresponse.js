'use strict';


/**
 * @ngdoc function
 * @name we99App.controller:DoseResponseCntrl
 * @description
 *
 * # DoseResponseCntrl
 * Controller of the we99App
 */
angular.module('we99App')
  .controller('DoseResponseCntrl', ["$scope", "RestService", function ($scope, RestService) {

    var v = new DataVis();
    var displayBoxLocation = "#scatter-plot";

    function transform(data){
      var result = [];
      data.forEach(function(plate){
        plate.data.forEach(function(well) {
          if (well.wellType !== 'NEG_CONTROL' & well.wellType !== "POS_CONTROL") {

            var found = false;

            result.forEach(function (r) {
              if (r.compound === well.compound) {
                found = true;
                r.wells.push(well);
              }
            });

            if(!found){
              result.push(
                {compound: well.compound, wells: []}
              );
            }

          }
        });
      });
      return result;
    }

    // Retrieve list of experiments
    RestService.getExperiments()
      .success(function (response) {

        // Experiment Drop Down
        $scope.experiments = response.values;
        console.log(response.values);
        $scope.selectedExperiment = $scope.experiments[0];


        $scope.$watch('selectedExperiment', function(newValue, oldValue) {
          $scope.compounds = transform(v.getDummmyPlateData(newValue.id).dataSets);
          $scope.compound = $scope.compounds[0];
          fullDisplayRefresh();

          $scope.$watch('compound', function(){
            fullDisplayRefresh();
          });
        });

      });

    function fullDisplayRefresh(){
      d3.select(displayBoxLocation).html("");

      var data = [];
      $scope.compound.wells.forEach(function(d){
        if(d.included){
          data.push( [d.amount, d.value] );
        }
      });

      var lineFit = v.linear_regression()
        .data(data)
        .line();

      v.renderScatterPlot({
        location: displayBoxLocation,
        data: $scope.compound.wells,
        xScaleIsDate: false,
        onCellClick: function(d) {
            $scope.compound.wells.forEach(function(dataPoint){
              if(dataPoint.wellIndex == d.wellIndex){
                dataPoint.included = !dataPoint.included;
              }
            });
          fullDisplayRefresh();
        },
        axisTitle:{
          x: "Dose",
          y: "Response"
        },
        scaleY: {min: 0.0, max: 1.0},
        lineFunction: function (x) { return lineFit(x) ; }
      });
    }

  }]);
