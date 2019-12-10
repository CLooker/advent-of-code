const massToFuel = require("../massToFuel");

const massToFuelRecur = (mass, fuel = 0) =>
  mass <= 0
    ? fuel
    : massToFuelRecur(
        massToFuel(mass),
        massToFuel(mass) > 0 ? fuel + massToFuel(mass) : fuel
      );

const getTotalFuel = masses =>
  masses.reduce((accum, mass) => accum + massToFuelRecur(mass), 0);

module.exports = getTotalFuel;
