'use strict';

describe('Directive: weAddplate', function () {

    // load the directive's module
    beforeEach(module('we99App'));

    var element,
        scope;

    beforeEach(inject(function ($rootScope) {
        scope = $rootScope.$new();
    }));


    // FIXME: TIM PLEASE TAKE A LOOK AT THIS AND SEE IF YOU CAN GET THIS WORKING
    //it('should make hidden element visible', inject(function ($compile) {
    //  element = angular.element('<we-addplate></we-addplate>');
    //  element = $compile(element)(scope);
    //  console.info(element);
    //  scope.$digest();
    //  expect(element.text()).toBe('wire test');
    //}));

    //it('should instantiate with an assay', function(){
    //  expect(scope.experiment).toBeDefined();
    //});

});

describe('Controller: AddPlateCtrl', function () {

    // load the controller's module
    beforeEach(module('we99App'));

    var AddPlateCtrl,
        scope,
        $httpBackend,
        LabelTableSvc,
        modalInstance;

    var samplePlateTypeResp = {
            values: [
                {
                    "id": 1,
                    "name": "Corning-3788",
                    "description": "Clear high-grade polystyrene for research diagnostic assays Formulated for uniform binding\\",
                    "dim": {
                        "rows": 8,
                        "cols": 12
                    },
                    "manufacturer": "Corning",
                    "orderLink": "http://www.coleparmer.com/Product/Corning_Plate_96_Well_ps_round_100_cs_Clear/UX-01728-07"
                },
                {
                    "id": 2,
                    "name": "BrandTech 384",
                    "description": "pureGrade BRANDplates feature non-treated\\",
                    "dim": {
                        "rows": 16,
                        "cols": 24
                    },
                    "manufacturer": "BrandTech",
                    "orderLink": "http://www.universalmedicalinc.com/BrandTech-384-Well-Plate-pureGrade-Non-Treated-p/781620-UMI.htm"
                }
            ]
        },
        samplePlateMapsResp = {
            values: [
                {
                    "id": 300,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 5,
                        "cols": 6
                    }
                },
                {
                    "id": 301,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 8,
                        "cols": 12
                    }
                },
                {
                    "id": 302,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 5,
                        "cols": 20
                    }
                },
                {
                    "id": 302,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 9,
                        "cols": 13
                    }
                },
                {
                    "id": 302,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 16,
                        "cols": 25
                    }
                }
            ]
        },
        sampleCompoundTypeAheadResp = {
            "totalCount": 10,
            "page": 100,
            "values": [
                {
                    "id": 17,
                    "name": "Ammonia"
                },
                {
                    "id": 9,
                    "name": "Ammonium acetate"
                },
                {
                    "id": 10,
                    "name": "Ammonium carbonate"
                },
                {
                    "id": 11,
                    "name": "Ammonium chloride"
                },
                {
                    "id": 12,
                    "name": "Ammonium dichromate"
                },
                {
                    "id": 13,
                    "name": "Ammonium hydroxide"
                },
                {
                    "id": 14,
                    "name": "Ammonium nitrate"
                },
                {
                    "id": 15,
                    "name": "Ammonium oxalate"
                },
                {
                    "id": 16,
                    "name": "Ammonium sulfate"
                },
                {
                    "id": 69,
                    "name": "Iron(II) ammonium sulfate"
                }
            ]
        };

    beforeEach(inject(function (_$httpBackend_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.whenGET('services/rest/plateType').respond(JSON.stringify(samplePlateTypeResp));
        $httpBackend.whenGET('services/rest/plateMap?maxCols=12&maxRows=8').respond(JSON.stringify(samplePlateMapsResp));
        $httpBackend.whenGET('services/rest/plateMap?maxCols=24&maxRows=16').respond(JSON.stringify(samplePlateMapsResp));
        $httpBackend.whenGET('services/rest/compound?pageSize=4&q=ammo').respond(JSON.stringify(sampleCompoundTypeAheadResp))
    }));

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope, _LabelTableSvc_) {
        LabelTableSvc = _LabelTableSvc_;
        scope = $rootScope.$new();
        scope.experimentName = "Project X";
        // Mock modalInstance for testing using spies
        modalInstance = {
          close: jasmine.createSpy('modalInstance.close'),
          dismiss: jasmine.createSpy('modalInstance.dismiss'),
          result: {
            then: jasmine.createSpy('modalInstance.result.then')
          }
        };

        AddPlateCtrl = $controller('AddPlateCtrl', {
            $scope: scope,
            $modalInstance: modalInstance,
            kCompoundUOM: {
                uM: 'MICROMOLAR',
                mM: 'MILLIMOLAR'
            },
            LabelTableSvc: LabelTableSvc,
            $routeParams: {
                experimentId: 1
            }
        });
    }));

    it('have a list of UOM options on load', function () {
        expect(scope.UOMOptions).toEqual([
            {key:'uM', value:'MICROMOLAR'},
            {key:'mM', value:'MILLIMOLAR'}
        ]);
    });

    describe('Plate Type', function() {
        it('should have a list of plate types on load', function () {
            $httpBackend.flush();
            expect(angular.equals(samplePlateTypeResp.values, scope.plateTypes)).toBe(true);
        });

        it('should initialize with no selectedPlateType', function () {
            expect(scope.selectedPlateType).toBeNull();
        });

        it('should NOT change plateTypes when a plate type is selected', function () {
            $httpBackend.flush();
            var expectedPlateTypes = angular.copy(scope.plateTypes);
            // valid selection
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            scope.$digest();
            expect(angular.equals(scope.plateTypes, expectedPlateTypes)).toBe(true);
            // invalid selection
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            scope.$digest();
            scope.selectedPlateType = null;
            scope.$digest();
            expect(angular.equals(scope.plateTypes, expectedPlateTypes)).toBe(true);
        });

        it('should change selectedPlateType when a plate type is selected', function () {
            // valid selection
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            scope.$digest();
            expect(scope.selectedPlateType).toEqual(samplePlateTypeResp.values[0]);
        });
    });

    describe('Plate Map', function() {
        it('should initialize with no plateMaps', function () {
            expect(scope.plateMaps).toBeNull();
        });

        it('should get a list of valid plateMaps when plateMapsForPlateType is triggered', function () {
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            expect(scope.selectedPlateType.dim.rows).toBe(8);
            expect(scope.selectedPlateType.dim.cols).toBe(12);
            $httpBackend.expectGET('services/rest/plateMap?maxCols=12&maxRows=8');
            scope.plateMapsForPlateType();
            $httpBackend.flush();
        });

        it('should change the list of valid plateMaps when a different plate type is selected', function () {
            var expectedPlateMaps = [
                {
                    "id": 300,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 5,
                        "cols": 6
                    }
                },
                {
                    "id": 301,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 8,
                        "cols": 12
                    }
                },
                {
                    "id": 302,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 5,
                        "cols": 20
                    }
                },
                {
                    "id": 302,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 9,
                        "cols": 13
                    }
                },
                {
                    "id": 302,
                    "name": "pme",
                    "description": "foo123",
                    "wells": [
                        {
                            "id": 800,
                            "coordinate": {
                                "row": 4,
                                "col": 5
                            },
                            "labels": [
                                {
                                    "name": "lbl1",
                                    "value": "ABC"
                                }
                            ],
                            "type": "COMP"
                        }
                    ],
                    "dim": {
                        "rows": 16,
                        "cols": 25
                    }
                }
            ];

            // Select a different selection
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            scope.$digest();
            expect(scope.selectedPlateType.dim.rows).toBe(8);
            expect(scope.selectedPlateType.dim.cols).toBe(12);
            $httpBackend.expectGET('services/rest/plateMap?maxCols=12&maxRows=8');
            scope.plateMapsForPlateType();
            $httpBackend.flush();

            // Change to desired selection
            scope.selectedPlateType = samplePlateTypeResp.values[1];
            scope.$digest();
            expect(scope.selectedPlateType.dim.rows).toBe(16);
            expect(scope.selectedPlateType.dim.cols).toBe(24);
            $httpBackend.expectGET('services/rest/plateMap?maxCols=24&maxRows=16');
            scope.plateMapsForPlateType();
            $httpBackend.flush();
        });

        it('should NOT change plateTypes or selectedPlateTypes when selected', function () {
            scope.plateTypes = samplePlateTypeResp.values;
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            $httpBackend.flush();
            scope.$digest();
            scope.plateMapsForPlateType();
            $httpBackend.flush();
            expect(angular.equals(scope.plateTypes, samplePlateTypeResp.values)).toBe(true);
            expect(angular.equals(scope.selectedPlateType, samplePlateTypeResp.values[0])).toBe(true);
        });

        it('should NOT change plateMaps when a plate map is selected', function () {
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            scope.$digest();

            var expectedPlateMaps = angular.copy(scope.plateMaps);

            // valid selection
            scope.selectedPlateMap = samplePlateMapsResp[0];
            scope.$digest();
            expect(angular.equals(scope.plateMaps, expectedPlateMaps)).toBe(true);
            // invalid selection
            scope.selectedPlateMap = samplePlateMapsResp[0];
            scope.$digest();
            scope.selectedPlateType = null;
            scope.$digest();
            expect(angular.equals(scope.plateMaps, expectedPlateMaps)).toBe(true);
        });
    });

    describe('label table', function () {
        var sampleMergeInfo;

        beforeEach(function () {
            sampleMergeInfo = {
                "plateMapId": 16,
                "plateType": {
                    "id": 16,
                    "name": "5x5",
                    "dim": {
                        "rows": 5,
                        "cols": 5
                    }
                },
                "mappings": [{
                    "label": "A",
                    "count": 4,
                    "wellType": "COMP"
                }, {
                    "label": "B",
                    "count": 4,
                    "wellType": "COMP"
                }, {
                    "label": "C",
                    "count": 1,
                    "wellType": "COMP"
                }]
            };
            $httpBackend.whenPOST(/services\/rest\/plateMap\/300\/merge*/).respond(JSON.stringify(sampleMergeInfo));
        });

        it('should grab the merge info for the plate map', function(){
            // Setup
            scope.selectedPlateType = samplePlateTypeResp.values[0];
            scope.$digest();
            scope.plateMapsForPlateType();
            $httpBackend.flush();
            scope.selectedPlateMap = scope.plateMaps[0];
            scope.$digest();

            // Run
            scope.getMergeInfo();
            $httpBackend.flush();

            // Compare label table entries with mapping entries
            var matches = sampleMergeInfo.mappings.length === scope.labelTable.length &&
                sampleMergeInfo.mappings.every(function(elem, index) {
                    return (elem.label === scope.labelTable[index].label &&
                            elem.count === scope.labelTable[index].count &&
                            elem.wellType === scope.labelTable[index].wellType);
                });
            expect(matches).toBe(true);
        });

        it('should not accept Replicates options counts < 0', function () {
            expect(function () {
                scope.computeReplicates(-1);
            }).toThrowError(Error);
        });

        it('should provide a options that are a factor of the count', function () {
            expect(scope.computeReplicates(0)).toEqual([]);
            expect(scope.computeReplicates(1)).toEqual([1]);
            expect(scope.computeReplicates(17)).toEqual([1, 17]);
            expect(scope.computeReplicates(10)).toEqual([1, 2, 5, 10]);
            expect(scope.computeReplicates(12)).toEqual([1, 2, 3, 4, 6, 12]);
        });

        it('should get compound typeahead values', function () {
            var actual = [];
            scope.findCompoundMatches('ammo').then(function (data) {
                actual = data;
            });
            $httpBackend.flush();
            expect(angular.equals(actual, sampleCompoundTypeAheadResp.values)).toBe(true);
        });

        it('should call LabelTableSvc.submitMergeInfo on submit', function(){
            inject(function($q){
                spyOn(LabelTableSvc, 'submitMergeInfo').and.callFake(function(){
                    return $q.defer().promise;
                });
                scope.submitPlateSet();
                expect(LabelTableSvc.submitMergeInfo).toHaveBeenCalled();
            });
        })
    });


    describe('Create Button', function(){
      var MergeType = {
        ADD: 'ADD',
        ADD_W_CMPD: 'ADD_W_CMPD',
        FULL_MONTY: 'FULL_MONTY'
      };

      describe('add plate', function(){
        it('should be disabled when there is an empty label table', function(){
          scope.labelTable = [];
          expect(scope.showCreateButton(MergeType.ADD)).toBe(false);
        });
        it('should be disabled when there is an empty compound option', function(){
          scope.labelTable =
            [{label: 'A', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100, compound: ""}, dilutionFactor: 2},
              {label: 'B', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100, compound: "def"}, dilutionFactor: 2}];
          expect(scope.showCreateButton(MergeType.ADD)).toBe(false);
        });
        it('should be enabled when all the compounds have values', function(){
          scope.labelTable =
            [{label: 'A', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100, compound: "abc"}, dilutionFactor: 2},
              {label: 'B', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100, compound: "def"}, dilutionFactor: 2}];
          expect(scope.showCreateButton(MergeType.ADD)).toBe(true);
        });
      });

      describe('add plate with compound list', function(){
        it('should be disabled when there is an empty label table', function(){
          scope.labelTable = [];
          expect(scope.showCreateButton(MergeType.ADD_W_CMPD)).toBe(false);
        });
        it('should be disabled when there is no csv file added', function(){
          scope.csvFiles = [];
          scope.labelTable =
            [{label: 'A', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100}, dilutionFactor: 2},
              {label: 'B', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100}, dilutionFactor: 2}];
          expect(scope.showCreateButton(MergeType.ADD_W_CMPD)).toBe(false);
        });
        it('should be enabled when all parameters set', function(){
          scope.csvFiles = ['something'];
          scope.labelTable =
            [{label: 'A', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100}, dilutionFactor: 2},
              {label: 'B', wellType: 'COMP', count: 4, replicates: 1, dose: {amount: 100}, dilutionFactor: 2}];
          expect(scope.showCreateButton(MergeType.ADD_W_CMPD)).toBe(true);

        });
      });

      describe('add plate full monty', function(){
        it('should be disabled when there is no csv file added', function(){
          scope.csvFiles = [];
          scope.selectedPlateType = 'fine china';
          expect(scope.showCreateButton(MergeType.FULL_MONTY)).toBe(false);
        });
        it('should be disabled when there is no plate type file added', function(){
          scope.csvFiles = ['selected'];
          scope.selectedPlateType = '';
          expect(scope.showCreateButton(MergeType.FULL_MONTY)).toBe(false);
        });
        it('should be enabled when all parameters are set', function(){
          scope.selectedPlateType = 'fine china';
          scope.csvFiles = ['something'];
          expect(scope.showCreateButton(MergeType.FULL_MONTY)).toBe(true);
        });
      });
    });
});

describe('Service: LabelTableSvc', function () {

    // load the service's module
    beforeEach(module('we99App'));

    // instantiate service
    var LabelTableSvc;

    beforeEach(inject(function (_LabelTableSvc_) {
        LabelTableSvc = _LabelTableSvc_;
    }));

    function sortLabels(l, r) {
        if (l.label > r.label) {
            return 1;
        }
        if (l.label < r.label) {
            return -1;
        }
        // a must be equal to b
        return 0;
    }

});
