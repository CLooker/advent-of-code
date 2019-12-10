const { memoize, pipe } = require("../utils");

const massToFuel = mass =>
  pipe(
    numerator => numerator / 3,
    Math.floor,
    num => num - 2
  )(mass);

module.exports = memoize(massToFuel);
