const masses = require('../2019.1.input.');
const getTotalFuel = require('./2019.1.1.solution');

describe('solution', () => {
  it('calculates the sum of the fuel requirements', () => {
    const actual = getTotalFuel(masses);
    const expected = 3412496;
    expect(actual).toBe(expected);
  });
});
