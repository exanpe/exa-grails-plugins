//= require lodash/lodash.min.js
//= require datatables/jquery.dataTables.min.js

// Global namespace
if (!Exa) {
    var Exa = {};
}

//================================================================================//
// Datatable
//================================================================================//

/**
 * Datatable component.
 *
 * @param {string} id				Unique client ID of the component
 * @param {array}  data				Data to display, in JSON format
 * @param {string} columns			Data columns to display
 * @param {boolean} auto    		Auto rendering DataTables or not
 * @param {boolean} filtering		Table filtering
 * @param {boolean} ordering		Columns ordering
 * @param {boolean} paging		    Table pagination
 * @param {boolean} infos		    Table informations
 * @param {boolean} stateSave       Save the state of a table or not (paging position, ordering state, etc)
 */
Exa.Datatable = function(id, data, columns, auto, filtering, ordering, paging, infos, stateSave) {
    this.id = id;
    this.data = data;
    this.columns = columns;
    this.auto = (auto === true);
    this.filtering = (filtering === true);
    this.ordering = (ordering === true);
    this.paging = (paging === true);
    this.infos = (infos === true);
    this.stateSave = (stateSave === true);
    this.options = {};
    this.instance = null;
};

/**
 * Internal instance prefix of the component
 *
 * @private
 * @constant
 * @static
 */
Exa.Datatable._PREFIX_ID = "exa-datatables-";

/**
 * Get instance of a given Datatable.
 *
 * @param id    Id of the component
 * @returns {null|*}
 */
Exa.Datatable.getDatatable = function(id) {
    var key = Exa.Datatable._PREFIX_ID + id;
    return window[key];
};

/**
 * Initializes Datatable with default settings.
 *
 * @private
 */
Exa.Datatable.prototype._init = function() {
    var key = Exa.Datatable._PREFIX_ID + this.id;
    window[key] = this;

    // Columns to display
    var columns = [];
    _.each(JSON.parse(this.columns), function(it) {
        var column = {};
        column.data = it;
        columns.push(column);
    });

    // Default options
    this.options = {
        data: this.data,
        // Filtering
        filter: this.filtering,
        // Ordering
        ordering: this.ordering,
        order: [],
        // Display infos
        info: this.infos,
        // Grid is responsive, but without all the details of each row
        responsive: {
            details: false
        },
        // Paging conf
        paging: this.paging,
        pageLength: 8,
        lengthMenu: [[8, 20, 50], [8, 20, 50]],
        // Local storage of the grid state
        stateSave: this.stateSave,
        // Columns
        columns: columns
    };

    // Auto rendering
    if (this.auto === true) {
        this.render({});
    }
};

/**
 * Set a Datatable option, according to the datatables.net documentation:
 * @see https://datatables.net/reference/option/
 *
 * @param key       Option key (pageLength, stateSave, order, ...)
 * @param value     Option value
 */
Exa.Datatable.prototype.setOption = function(key, value) {
  this.options[key] = value;
};

/**
 * Add a custom column to the datatable.
 *
 * @param column    Column to add before rendering
 */
Exa.Datatable.prototype.addColumn = function(column) {
    this.options.columns = this.options.columns || [];
    this.options.columns.push(column);
};

/**
 * Get the real datatables.net instance wrapped by Datatable component.
 *
 * @returns {null|*}
 */
Exa.Datatable.prototype.getInstance = function() {
  return this.instance;
};

/**
 * Render the Datatable.
 *
 * @param options   Options to override
 */
Exa.Datatable.prototype.render = function(options) {
    if (options) {
        _.extend(this.options, options);
    }
    this.instance = $('#' + this.id).DataTable(this.options);
};