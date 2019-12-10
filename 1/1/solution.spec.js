const masses = require("./input");
const getTotalFuel = require("./solution");

describe("solution", () => {
  it("calculates the sum of the fuel requirements", () => {
    const actual = getTotalFuel(masses);
    const expected = 3412496;
    expect(actual).toBe(expected);
  });
});
