const solution = require('./2019.6.1.solution.js');
const input = require('./2019.6.1.solution.spec.js');

describe('2019.6.1.solution', () => {
  it('solves the problem', () => {
    const actual = solution(input);
    console.log(actual);
  });
});