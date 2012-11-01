angular.module('edit-address', ['ngSanitize'])
.directive('goEditAddress', function(linkyFilter) {
  return {
    link: function(scope, elem) {
      var edit = elem.find('textarea');
      var render = elem.find('.render');
      
      scope.address = edit.html();
      
      render.click(function() {
        scope.$apply(function() {
          scope.toggleEdit();
        });
      });
      
      edit.focusout(function() {
        scope.$apply(function() {
          scope.toggleEdit();
        })
      });
      
      scope.$watch('editing', function(editing) {
        if (editing) {
          edit.show().focus(); render.hide();
        } else {
          render.empty();
          var address = [];
          angular.forEach(scope.address.split('\n'), function(line, idx) {
            var first = idx === 0;
            line = line.trim();
            render.append((first ? '<strong>' : '') + linkyFilter(line) + (first ? '</strong>' : ''));
            render.append('<br>');
            address.push(line);
          });
          scope.address = address.join('\n');
          edit.hide(); render.show();
        }
      });
    }
  }
})
.controller('AddressEditController', function($scope) {
  $scope.address = '';
  $scope.editing = false;
  $scope.toggleEdit = function() {
    $scope.editing = !$scope.editing;
  }
});
