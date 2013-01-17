jQuery(document).ready(function($) {

  /**
   * Query
   * ---------------------------------------------------------------------
   */

  var $querySelect = $('#query-select');
  var $queryNamespaces = $('.query-namespaces');

  // Creates the CodeMirror editor.
  var queryEditor = CodeMirror.fromTextArea(document.getElementById("query-editor"), {
    mode: "application/x-sparql-query",
    tabMode: "indent",
    matchBrackets: true,
    lineNumbers: true,
    lineWrapping: true,
    theme: "elegant"
  });

  // Plugin to match selected tokens.
  queryEditor.on("cursorActivity", function() {
    queryEditor.matchHighlight("CodeMirror-matchhighlight");
  });

  // At each query change, we must reload the editor with the new query and reload the namespaces.
  $querySelect.change(function(e){
    selected = $(this).find(':selected');
    queryEditor.setValue(selected.attr('data-query'));
    reloadReferencedNamespaces();
  });

  // Loads the queries from a JSON file.
  $.getJSON('data/queries.json', function(data) {
    var firstQuery;
    $.each(data, function(key, query) {
      // Initialize the editor with the first Query.
      firstQuery = !firstQuery ? query : firstQuery;
      // All the queries are put into the select with custom data attributes.
      $querySelect.append('<option value="'+key+'" data-query="'+query.string+'" data-description="'+query.description+'">'+query.title+'</option>');
    });
    $querySelect.first().attr('selected', true);
    queryEditor.setValue($('<div/>').html(firstQuery.string).text());
    reloadReferencedNamespaces();
  });

  // Manages extracting namespace URIs from the query string.
  function extractNamespaces(queryString) {
    queryString = queryString.split('SELECT')[0];
    namespaces = queryString.match(/ (.*?): <(.*?)>/mg);
    return namespaces;
  }

  // Transforms namespace URIs into links pointing to documentations.
  function displayNamespacesURLs(queryString) {
    namespaces = extractNamespaces(queryString);
    $queryNamespaces.empty();
    for (var i = 0; i < namespaces.length; i++) {
      prefix = namespaces[i].replace(/>/,'').split(/: </);
      $queryNamespaces.append('<li>'+prefix[0]+': <a href="'+prefix[1]+'">'+prefix[1]+'</a></li>');
    }
  }

  function reloadReferencedNamespaces() {
    displayNamespacesURLs(queryEditor.getValue());
  }

  function prependToEditor(str) {
    queryEditor.setValue(str + queryEditor.getValue());
  }

  // Calls the prefix.cc API to add namespaces to our query.
  $('#prefix-button').click(function (e){
    e.preventDefault();
    prefixId = $('#prefix-text').val();
    $.getJSON('http://prefix.cc/'+prefixId+'.file.json', function(data) {
      newPrefix = 'PREFIX ' + prefixId + ': ';
      $.each(data, function(key, val) {
        prependToEditor('PREFIX ' + prefixId + ': ' + '<' + val + '>\n');
      });
      reloadReferencedNamespaces();
    });
  });

  /**
   * Results
   * ---------------------------------------------------------------------
   */

  var $resultsColumns = $('.results-columns');
  var $resultsRows = $('.results-rows');

  var $queryResult;

  // Loads the results from a JSON file.
  $.getJSON('data/results.json', function(data) {
    $queryResult = data;
    var aoColumns = [];
    // First we parse the columns to retrieve their pretty names and types.
    for(var i = 0; i < data.columns.length; i++) {
      $resultsColumns.append('<td>'+data.columns[i].title+'</td>');
      aoColumns[i] = {'sType': data.columns[i].sType};
    }

    // Then we retrieve the data itself.
    for (var j = 0; j < data.results.length; j++) {
      var currentCells = '';
      $.each(data.results[j], function (key, val) {
        currentCells += '<td>'+val+'</td>';
      });
      $resultsRows.append('<tr>'+currentCells+'</tr>');
    }

    // Create the data table with the right types.
    $('.observation-table').dataTable({'aoColumns': aoColumns});
  });

  /**
   * Environment
   * ---------------------------------------------------------------------
   */

  $environmentSelection = $('.environment-selection');

  $.getJSON('data/environment.json', function(data) {
    var datasourceHTML = '';
    var environmentHTML = '';

    var datastores = {};

    for (var j = 0; j < data.datastores.length; j++) {
      datastores[data.datastores[j].name] = data.datastores[j];
    }

    for (var i = 0; i < data.datasources.length; i++) {
      datasourceHTML += '<fieldset class="span4"><legend>'+data.datasources[i].title+'<small class="pull-right">'+data.datasources[i].size+'Mo</small></legend><div class="control-group"><select form="query-form" class="environment-select">';

      for (var k = 0; k < data.datasources[i].locations.length; k++) {
        datasourceHTML += '<option data-name="'+data.datasources[i].locations[k]+'" data-description="'+datastores[data.datasources[i].locations[k]].description+'">'+datastores[data.datasources[i].locations[k]].title+'</option>';
      }

      datasourceHTML += '</select><p class="help-block">'+data.datasources[i].description+'</p></div></fieldset>';
      if (i !== 0 && (i + 1) % 3 === 0) {
        environmentHTML += '<div class="row-fluid">'+datasourceHTML+'</div>';
        datasourceHTML = '';
      }
    }
    $environmentSelection.append(environmentHTML);
  });

$('.environment-select').popover({
  'html' : true,
  'trigger' : 'focus',
  'placement' : 'bottom',
  'title' : function() {
    alert('!');
    return $(this).find(':selected').val();
  },
  'content' : function() {
    return $(this).find(':selected').attr('data-description');
  }
});

  /**
   * About
   * ---------------------------------------------------------------------
   */

  $('.contributor img').tooltip({'placement' : 'bottom'});

});


