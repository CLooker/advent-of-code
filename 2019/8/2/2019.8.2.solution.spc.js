const solution = require('./2019.8.2.solution.js');
const input = require('./2019.8.2.solution.spec.js');

describe('2019.8.2.solution', () => {
  it('solves the problem', () => {
    const actual = solution(input);
    console.log(actual);
  });
});