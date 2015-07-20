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
 */
Exa.Datatable = function(id, data, columns) {
    this.id = id;
    this.data = data;
    this.columns = columns;
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
    _.each(_.words(this.columns, /[^,? ]+/g), function(word) {
        var column = {};
        column.data = word;
        columns.push(column);
    });

    // Default options
    this.options = {
        data: this.data,
        // Filtering
        filter: true,
        // Ordering
        ordering: true,
        order: [],
        // Display infos
        info: true,
        // Grid is responsive, but without all the details of each row
        responsive: {
            details: false
        },
        // Paging conf
        paging: true,
        pageLength: 8,
        lengthMenu: [[8, 20, 50], [8, 20, 50]],
        // Local storage of the grid state
        stateSave: false,
        // Columns
        columns: columns
    };
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