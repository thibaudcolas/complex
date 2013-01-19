<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <!-- DNS prefetch shaves a few milliseconds when working with CDNs. -->
        <link rel="dns-prefetch" href="//fonts.googleapis.com">
        <link rel="dns-prefetch" href="//ajax.googleapis.com">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Triple Bench</title>

        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link href='http://fonts.googleapis.com/css?family=Open+Sans&amp;subset=latin' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/lib/bootstrap.min.css">
        <link rel="stylesheet" href="css/lib/dataTables.bootstrap.css">
        <link rel="stylesheet" href="css/lib/codemirror.css">
        <link rel="stylesheet" href="css/lib/elegant.css">
        <link rel="stylesheet" href="css/main.css">

        <!-- Modernizr is a polyfill library : enhances old browser support for new (HTML5) features. -->
        <script src="js/lib/modernizr-latest.js"></script>
    </head>
    <body>
        <!-- container-fluid = Responsive layout. -->
        <div id="content" class="container-fluid">
            <header>
                <span class="f1 pull-left"></span>
                <span class="f2 pull-left"></span>
                <span class="f3 pull-left"></span>

                <a href="https://github.com/masalthunlass/complex"><img style="position: absolute; top: 0; right: 5%; border: 0;" src="img/forkme_right_red_aa0000.png" alt="Fork me on GitHub"></a>

                <h1>Triple Bench <img src="img/bench-logo.png"> <small>Find a sturdy home for your triples</small></h1>
            </header>

            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#query" data-toggle="tab"><i class="icon-search"></i> Query</a></li>
                    <li><a href="#environment" data-toggle="tab"><i class="icon-cog"></i> Environment</a></li>
                    <!-- Those three tabs can only be accessed after executing a query. -->
                    <li><a href="#results" data-toggle="tab"><i class="icon-th"></i> Results</a></li>
                    <li><a href="#visualization" data-toggle="tab"><i class="icon-picture"></i> Visualization</a></li>
                    <li><a href="#benchmark" data-toggle="tab"><i class="icon-time"></i> Benchmark</a></li>
                    <li><a href="#history" data-toggle="tab"><i class="icon-calendar"></i> History</a></li>
                    <li><a href="#about" data-toggle="tab"><i class="icon-bullhorn"></i> About</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="query">
                        <form class="row-fluid" method="get" name="query-form" id="query-form">

                            <fieldset class="span8">
                                <div class="control-group">
                                    <!-- This select is populated with a JSON file at runtime. -->
                                    <select id="query-select">
                                    </select>
                                    <!-- This textarea is handled by Code Mirror and populated at runtime. -->
                                    <textarea id="query-editor" name="query-editor">
                                    </textarea>
                                </div>
                            </fieldset>

                            <fieldset class="span4">
                                <!-- Oddly placed SEND QUERY button. -->
                                <div class="control-group">
                                    <button type="submit" class="btn btn-block btn-primary">Send</button>
                                </div>
                                <!-- Parameters to customize our query execution. -->
                                <div class="control-group">
                                    <label for="set-limit" class="checkbox">
                                        <input type="checkbox" id="set-limit" name="set-limit" value="true" checked/>
                                        Limit to the first 500 results
                                    </label>
                                    <label for="use-inference" class="checkbox">
                                        <input type="checkbox" id="use-inference" name="use-inference" value="true" checked/>
                                        Use an inference engine
                                    </label>
                                </div>

                                <hr/>

                                <!-- A handy little box using the prefix.cc API to add prefixes to our queries. -->
                                <div class="control-group">
                                    <div class="input-prepend input-append">
                                        <span class="add-on"><i class="icon-plus"></i></span>
                                        <input id="prefix-text" type="text" placeholder="Enter a namespace to add" form="test-no-form-exists"/>
                                        <button id="prefix-button" class="btn" type="button">Go !</button>
                                    </div>

                                    <h5>Referenced Namespaces</h5>
                                    <!-- This list is populated by links to our current namespaces at runtime. -->
                                    <ul class="query-namespaces">
                                    </ul>
                                </div>
                            </fieldset>
                        </form>
                    </div>

                    <div class="tab-pane" id="environment">
                        <!-- Emmet FTW ! -->
                        <!-- (div.row-fluid>(fieldset.span4>legend+div.control-group.span4>select>option*3)*3)*2 -->
                        <fieldset class="environment-selection">
                        </fieldset>
                    </div>

                    <div class="tab-pane" id="results">
                        <!-- Uses the DataTables plugin. -->
                        <table class="observation-table table table-striped table-condensed table-bordered">
                            <thead>
                                <!-- Filled when the query results are returned. -->
                                <tr class="results-columns">
                                </tr>
                            </thead>
                            <!-- Filled when the query results are returned. -->
                            <tbody class="results-rows">
                                <!-- Emmet FTW ! -->
                                <!-- tr*15>td*3>lorem2 -->
                            </tbody>
                        </table>
                    </div>

                    <div class="tab-pane" id="visualization">
                        <div id="google-chart" style="height:350px"></div>
                        <div class="input-prepend input-append">
                            <span class="add-on"><i class="icon-refresh"></i></span>
                            <select name="type" id="chart-type-select"></select>
                            <p class="add-on">Refresh</p>
                        </div>
                    </div>

                    <div class="tab-pane" id="benchmark">
                        <!-- Filled by D3 with SVG. -->
                    </div>

                    <div class="tab-pane" id="history">
                        <div class="row-fluid history-header">
                            <div class="span2">
                                <h5><i class="icon-time"></i> Timestamp</h5>
                            </div>
                            <div class="span4">
                                <h5><i class="icon-search"></i> Query</h5>
                            </div>
                            <div class="span6">
                                <h5><i class="icon-cog"></i> Environment</h5>
                            </div>
                        </div>
                        <div class="history-items">
                            <!-- Retrieved at runtime. -->
                        </div>

                    </div>

                    <div class="tab-pane" id="about">
                        <div class="row-fluid">
                            <div class="span3 contributor">
                                <a href="http://github.com/violethaze"><img title="Namrata Patel" class="img-polaroid" src="http://gravatar.com/avatar/fb2a2905d2b472c00ee1ccf265942646?s=150" alt="Namrata Patel's Gravatar"></a>
                            </div>
                            <div class="span3 contributor">
                                <a href="http://github.com/masalthunlass"><img title="Mathilde Salthun-Lassalle" class="img-polaroid" src="http://gravatar.com/avatar/0d302cb3abb7a7473fb7a339f4299d40?s=150" alt="Mathilde Salthun-Lassalle's Gravatar"></a>
                            </div>
                            <div class="span3 contributor">
                                <a href="http://github.com/marminthibaut"><img title="Thibaut Marmin" class="img-polaroid" src="http://gravatar.com/avatar/1f24a65d8e4cc31761529b950927a706?s=150" alt="Thibaut Marmin's Gravatar"></a>
                            </div>
                            <div class="span3 contributor">
                                <a href="http://github.com/thibweb"><img title="Thibaud Colas" class="img-polaroid" src="http://gravatar.com/avatar/634a765b36b969d27c5ac67c09c41c13?s=150" alt="Thibaud Colas's Gravatar"></a>
                            </div>
                        </div>
                        <hr/>
                        <div class="row-fluid">
                            <div class="span6">
                                <p>
                                    Triple Bench is an experiment around RDF storage performance and RDF exploration via SPARQL.
                                    It was built by four master students of the <a href="http://www.univ-montp2.fr">Université Montpellier 2</a> during a course on Semantic Web technologies and NoSQL databases.
                                    Triple Bench tries to combine the best of both worlds and to experiment with as much data and as much storage solutions as possible to compare them and make them stand out from each other.
                                </p>
                            </div>
                            <div class="span6">
                                <p>
                                    This Web application is built in Java EE with the <a href="http://jena.apache.org">Apache Jena</a> RDF framework.
                                    It also uses a custom built version of <a href="http://d2rq.org">D2RQ</a> and multiple databases, relational (<a href="http://www.postgresql.org/">PostgreSQL</a>, <a href="https://www.mysql.com/">MySQL</a>, <a href="http://www.xeround.com">Databases As A Service</a>) and NoSQL (<a href="http://www.neo4j.org/">Neo4J</a>).
                                    Finally, the interface is built with <a href="http://twitter.github.com/bootstrap/">Twitter Bootstrap</a>, <a href="http://jquery.com/">jQuery</a>, <a href="http://codemirror.net/">CodeMirror</a> and <a href="http://d3js.org/">D3.js</a>.
                                    <br/>Our data is from <a href="http://rdf.insee.fr/">INSEE</a>, <a href="http://www.nosdonnees.fr/">Nos Données.fr</a>, <a href="http://www.passim.info/">PASSIM</a> and <a href="http://www.geonames.org/">GeoNames</a>.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  <!-- /container -->

            <footer>
                <div class="container">
                    <hr/>
                    <p>An exploration of RDF storage solutions. By <a href="https://github.com/thibweb/">Thibaud Colas</a>, <a href="https://github.com/marminthibaut/">Thibaut Marmin</a>, <a href="https://github.com/violethaze/">Namrata Patel</a> and <a href="http://github.com/masalthunlass">Mathilde Salthun-Lassalle</a>.</p>
                    <p>© 2013 &middot; Built with <a href="https://jena.apache.org/">Apache Jena</a>.</p>
                </div>
            </footer>

        </div>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/lib/jquery-1.9.0.min.js"><\/script>')</script>
        <script src="//www.google.com/jsapi"></script>
        <script src="js/lib/bootstrap.min.js"></script>
        <script src="js/lib/d3.v3.min.js"></script>
        <script src="js/lib/jquery.dataTables.min.js"></script>
        <script src="js/lib/dataTables.bootstrap.js"></script>
        <script src="js/lib/codemirror/codemirror.js"></script>
        <script src="js/lib/codemirror/matchbrackets.js"></script>
        <script src="js/lib/codemirror/searchcursor.js"></script>
        <script src="js/lib/codemirror/match-highlighter.js"></script>
        <script src="js/lib/codemirror/sparql.js"></script>
        <script type="text/javascript">
            // Tells the Google Loader what to load from the Visualization API.
            google.load('visualization', '1.0', {'packages':['annotatedtimeline','corechart','geomap','geochart','map','treemap']});
        </script>
        <script src="js/visualization.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
