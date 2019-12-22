const masses = require('../2019.1.input.');
const getTotalFuel = require('./2019.1.2.solution');

describe('solution', () => {
  it('calculates the sum of the fuel requirements', () => {
    const actual = getTotalFuel(masses);
    const expected = 5115845;
    expect(actual).toBe(expected);
  });
});
