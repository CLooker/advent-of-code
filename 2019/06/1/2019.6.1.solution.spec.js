const solution = require('./2019.6.1.solution.js');
const input = require('../2019.6.input.js');

describe('2019.6.1.solution', () => {
  it('solves the problem', () => {
    const actual = solution(input);
    const expected = 254447;
    expect(actual).toBe(expected);
  });
});
