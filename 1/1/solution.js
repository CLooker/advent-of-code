const massToFuel = require("../massToFuel");

const getTotalFuel = masses =>
  masses.reduce((accum, mass) => accum + massToFuel(mass), 0);

module.exports = getTotalFuel;
