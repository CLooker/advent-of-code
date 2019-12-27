const solution = require('./2019.5.2.solution.js');
const input = require('../2019.5.input.js');

describe('2019.5.2.solution', () => {
  it('solves the problem', () => {
    const actual = solution(input);
    const expected = 11189491;
    expect(actual).toBe(expected);
  });
});
