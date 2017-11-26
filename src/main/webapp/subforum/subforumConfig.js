(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {

            $stateProvider
                .state("subforums", {
                    url: "/subforums",
                    templateUrl: "subforum/subforums.html",
                    controller: "subforumsController"
                })

                .state("subforum", {
                    url: "/subforums/{title}",
                    templateUrl: "subforum/subforum.html",
                    controller: "subforumController",
                    resolve: {
                        subforumPromise: function(subforumService, $transition$) {
                            return subforumService.getSubforum($transition$.params().title);
                        }
                    }
                });
        }
        ]);
})();

