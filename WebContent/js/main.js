jQuery(document).ready(function($) {

 // "use strict";

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
  $.getJSON('data/npd.json', function(data) {
    $queryResult = data;
    $resultsColumns.append('<th>'+data.head.vars.join('</th><th>')+'</th>');

    visualization.go(data, 'gLineChart');

    // Then we retrieve the data itself.
    for (var j = 0; j < data.results.bindings.length; j++) {
      var currentCells = '';
      $.each(data.results.bindings[j], function (key, val) {
        currentCells += '<td>'+val.value+'</td>';
      });
      $resultsRows.append('<tr>'+currentCells+'</tr>');
    }

    // Create the data table with the right types.
    $('.observation-table').dataTable();
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
    var datastoreHTML = '';

    for (var j = 0; j < data.datastores.length; j++) {
      datastores[data.datastores[j].name] = data.datastores[j];
      datastoreHTML += '<div class="span2"><h5>'+data.datastores[j].title+'</h5><p>'+data.datastores[j].description+'</p></div>';
    }

    for (var i = 0; i < data.datasources.length; i++) {
      datasourceHTML += '<fieldset class="span4"><legend>'+data.datasources[i].title+'<small class="pull-right">'+data.datasources[i].size+'Mo</small></legend><div class="control-group"><select name="'+data.datasources[i].name+'" form="query-form" class="environment-select" data-source="'+data.datasources[i].name+'">';

      for (var k = 0; k < data.datasources[i].locations.length; k++) {
        datasourceHTML += '<option value="'+data.datasources[i].locations[k]+'">'+datastores[data.datasources[i].locations[k]].title+'</option>';
      }

      datasourceHTML += '</select><p class="help-block">'+data.datasources[i].description+'</p></div></fieldset>';
      if (i !== 0 && (i + 1) % 3 === 0) {
        environmentHTML += '<div class="row-fluid">'+datasourceHTML+'</div>';
        datasourceHTML = '';
      }
    }
    $environmentSelection.append(environmentHTML + '<hr/><div class="row-fluid">' + datastoreHTML + '</div>');
  });

  /**
   * Visualization
   * ---------------------------------------------------------------------
   */

  $("#chart-type-select").change(function (){
    visualization.drawChart($(this).val());
  });

  /**
   * History
   * ---------------------------------------------------------------------
   */

  var historyKey = 'queryHistory';
  storageSetJSON(historyKey, {"items" : [{
    "timestamp" : "05:13:25",
    "query" : "PREFIX+a%3A+<http%3A%2F%2Fwww.w3.org%2F2000%2F10%2Fannotation-ns%23>%0D%0APREFIX+dc%3A+<http%3A%2F%2Fpurl.org%2Fdc%2Felements%2F1.1%2F>%0D%0APREFIX+foaf%3A+<http%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F>%0D%0A%0D%0A%23+Comment!%0D%0A%0D%0ASELECT+%3Fgiven+%3Ffamily%0D%0AWHERE+{%0D%0A++%3Fannot+a%3Aannotates+<http%3A%2F%2Fwww.w3.org%2FTR%2Frdf-sparql-query%2F>+.%0D%0A++%3Fannot+dc%3Acreator+%3Fc+.%0D%0A++OPTIONAL+{%3Fc+foaf%3Agiven+%3Fgiven+%3B%0D%0A+++++++++++++++foaf%3Afamily+%3Ffamily+}+.%0D%0A++FILTER+isBlank(%3Fc)%0D%0A+}",
    "environment" : {
      "inseecog" : "memory",
      "inseepop" : "tdb",
      "passim" : "sdb",
      "d2rq" : "memory",
      "isf" : "d2rq",
      "taweb" : "sesame"
    }
  }]});

  function storageGetJSON(key) {
    var retrievedObject = localStorage.getItem(key);
    return JSON.parse(retrievedObject);
  }

  function storageSetJSON(key, json) {
    var storedObject = JSON.stringify(json);
    localStorage.setItem(key, storedObject);
  }

  $.getJSON('data/history.json', function(data) {
    var storageData = storageGetJSON(historyKey);
    for (var i = storageData.items.length - 1; i >= Math.max(0, storageData.items.length - 6); i--) {
      addHistoryItem(storageData.items[i], i);
    }
  });

  function addHistoryItem(item, index) {
    var htmlHistoryItem = '';
    var itemEnvironment = JSON.stringify(item.environment);
    var displayQuery = decodeURIComponent(item.query).replace(/\+/g,' ').replace(/\n/g, ' ');
    displayQuery = displayQuery.substring(displayQuery.indexOf('SELECT'));

    htmlHistoryItem += '<time class="add-on history-timestamp">'+item.timestamp+'</time>';
    htmlHistoryItem += '<input type="text" data-query="'+item.query+'" data-env="'+itemEnvironment+'" value="'+displayQuery+'" id="history-item'+index+'" class="history-query span3" disabled/>';
    htmlHistoryItem += '<p class="add-on">'+itemEnvironment.replace(/"/g,'')+'</p>';
    htmlHistoryItem += '<button class="btn history-btn" type="button">Reuse</button>';

    $('.history-items').append('<div class="row-fluid"><div class="input-prepend input-append span12">'+htmlHistoryItem+'</div></div>');
  }

  /**
   * About
   * ---------------------------------------------------------------------
   */

  $('.contributor img').tooltip({'placement' : 'bottom'});

});


