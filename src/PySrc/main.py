from PySrc.graphics import *
from time import sleep
from random import randint

SIZE = 1000
color_list = ['red', 'purple', 'yellow', 'green', 'blue', 'cyan', 'orange', 'maroon', 'magenta', 'pink', 'violet']


def determine_quadrant(deficiency, n):
    if deficiency[0] < n / 2:
        if deficiency[1] < n / 2:
            return 2
        else:
            return 3
    else:
        if deficiency[1] < n / 2:
            return 1
        else:
            return 4


class QuadrantRenderer(object):
    def __init__(self, n, init_deficiency):
        self.win = GraphWin("Quandrant Renderer", 1000, 1000)
        self.n = n
        self.sleep = float(input("How long between drawing of trominoes (in seconds)?: "))
        self.init_deficiency = init_deficiency
        self.grid_size = int(1000 / n)

    def render(self):
        self.draw_init_grid()
        input("Press Enter to Begin Tiling")
        self.draw_trominoes(self.init_deficiency, self.n, 0, 0)
        input("Press Enter to Close")
        self.win.close()

    def draw_init_grid(self):
        for x in range(0, self.n):
            for y in range(0, self.n):
                if self.init_deficiency[0] == x and self.init_deficiency[1] == y:
                    r = Rectangle(Point(x * self.grid_size, y * self.grid_size),
                                  Point(x * self.grid_size + self.grid_size,
                                        y * self.grid_size + self.grid_size))
                    r.setFill(color="black")
                else:
                    r = Rectangle(Point(x * self.grid_size, y * self.grid_size),
                                  Point(x * self.grid_size + self.grid_size,
                                        y * self.grid_size + self.grid_size))
                r.draw(self.win)

    def draw_trominoes(self, deficiency, n, offset_column, offset_row):
        quadrant = determine_quadrant([deficiency[0] - offset_column, deficiency[1] - offset_row], n)
        print(quadrant)
        if quadrant == 1:
            m1 = [n / 2 - 1 + offset_column, n / 2 + offset_row]
            m2 = [m1[0] + 1, m1[1]]
            m3 = [m1[0], m1[1] - 1]
            self.draw_tromino(m1, m2, m3)
            sleep(self.sleep)
            if n != 2:
                self.draw_trominoes(deficiency, n / 2, offset_column + n / 2, offset_row)
                self.draw_trominoes(m1, n / 2, offset_column, offset_row + n / 2)
                self.draw_trominoes(m2, n / 2, offset_column + n / 2, offset_row + n / 2)
                self.draw_trominoes(m3, n / 2, offset_column, offset_row)
        elif quadrant == 2:
            m1 = [n / 2 + offset_column, n / 2 + offset_row]
            m2 = [m1[0] - 1, m1[1]]
            m3 = [m1[0], m1[1] - 1]
            self.draw_tromino(m1, m2, m3)
            sleep(self.sleep)
            if n != 2:
                self.draw_trominoes(deficiency, n / 2, offset_column, offset_row)
                self.draw_trominoes(m1, n / 2, offset_column + n / 2, offset_row + n / 2)
                self.draw_trominoes(m2, n / 2, offset_column, offset_row + n / 2)
                self.draw_trominoes(m3, n / 2, offset_column + n / 2, offset_row)
        elif quadrant == 3:
            m1 = [n / 2 + offset_column, n / 2 - 1 + offset_row]
            m2 = [m1[0] - 1, m1[1]]
            m3 = [m1[0], m1[1] + 1]
            self.draw_tromino(m1, m2, m3)
            sleep(self.sleep)
            if n != 2:
                self.draw_trominoes(deficiency, n / 2, offset_column, offset_row + n / 2)
                self.draw_trominoes(m1, n / 2, offset_column + n / 2, offset_row)
                self.draw_trominoes(m2, n / 2, offset_column, offset_row)
                self.draw_trominoes(m3, n / 2, offset_column + n / 2, offset_row + n / 2)
        elif quadrant == 4:
            m1 = [n / 2 - 1 + offset_column, n / 2 - 1 + offset_row]
            m2 = [m1[0] + 1, m1[1]]
            m3 = [m1[0], m1[1] + 1]
            self.draw_tromino(m1, m2, m3)
            sleep(self.sleep)
            if n != 2:
                self.draw_trominoes(deficiency, n / 2, offset_column + n / 2, offset_row + n / 2)
                self.draw_trominoes(m1, n / 2, offset_column, offset_row)
                self.draw_trominoes(m2, n / 2, offset_column + n / 2, offset_row)
                self.draw_trominoes(m3, n / 2, offset_column, offset_row + n / 2)

    def draw_tromino(self, m1, m2, m3):
        color = color_list[randint(0, len(color_list) - 1)]
        r = Rectangle(Point(m1[0] * self.grid_size, m1[1] * self.grid_size),
                      Point(m1[0] * self.grid_size + self.grid_size,
                            m1[1] * self.grid_size + self.grid_size))
        r.setFill(color)
        r.draw(self.win)
        r = Rectangle(Point(m2[0] * self.grid_size, m2[1] * self.grid_size),
                      Point(m2[0] * self.grid_size + self.grid_size,
                            m2[1] * self.grid_size + self.grid_size))
        r.setFill(color)
        r.draw(self.win)
        r = Rectangle(Point(m3[0] * self.grid_size, m3[1] * self.grid_size),
                      Point(m3[0] * self.grid_size + self.grid_size,
                            m3[1] * self.grid_size + self.grid_size))
        r.setFill(color)
        r.draw(self.win)


def main():
    k = int(input("Welcome to Trominoes. Please enter a value 1 <= k <= 7 for the size of the grid = 2^k: "))
    if k < 1 or k > 7:
        print("The value entered must be an integer between 1, 7 (inclusive)")
        return
    n = pow(2, k)
    init_deficiency = [0, 0]
    init_deficiency[1] = int(input("Which row would you like the deficiency in (0-indexed)?: "))
    init_deficiency[0] = int(input("Which column would you like the deficiency in (0-indexed)?: "))
    if init_deficiency[0] < 0 or init_deficiency[0] > n - 1 or init_deficiency[1] < 0 or init_deficiency[1] > n - 1:
        print("The row and column must be integer within [0, (2^k)-1]")
        return
    algo = input("Which algorithm would you like to use (L-Shape or Quadrants)? (L/Q): ")
    if algo == "Q":
        QuadrantRenderer(n, init_deficiency).render()
    elif algo == "L":
        pass
    else:
        print("Please provide a valid option next time.")
        return


if __name__ == '__main__':
    main()
