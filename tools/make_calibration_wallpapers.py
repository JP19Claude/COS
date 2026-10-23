#!/usr/bin/env python3
"""Erzeugt Kalibrier-Wallpaper für die Referenzaufnahmen.

checker_360.png   Schwarz-weißes Schachbrett mit 360-px-Feldern. Harte Kanten für die
                  Blur-Messung, reine Schwarz-/Weiß-Flächen für Alpha und Tönung von Overlays.
grid_numbered.png Nummeriertes Raster für Parallax-, Zoom- und Verschiebungs-Messungen.

Aufruf: python3 tools/make_calibration_wallpapers.py <ausgabeordner>
"""
import sys
from pathlib import Path

from PIL import Image, ImageDraw, ImageFont

WIDTH, HEIGHT = 1440, 3200
CELL = 360
GRID = 80


def checker(path: Path) -> None:
    img = Image.new("RGB", (WIDTH, HEIGHT), "white")
    draw = ImageDraw.Draw(img)
    for row in range(HEIGHT // CELL + 1):
        for col in range(WIDTH // CELL + 1):
            if (row + col) % 2 == 0:
                x, y = col * CELL, row * CELL
                draw.rectangle([x, y, x + CELL - 1, y + CELL - 1], fill="black")
    img.save(path)


def grid(path: Path) -> None:
    img = Image.new("RGB", (WIDTH, HEIGHT), (128, 128, 128))
    draw = ImageDraw.Draw(img)
    font = ImageFont.load_default(size=28)
    for x in range(0, WIDTH, GRID):
        major = x % (GRID * 5) == 0
        draw.line([x, 0, x, HEIGHT], fill="white" if major else (190, 190, 190), width=3 if major else 1)
    for y in range(0, HEIGHT, GRID):
        major = y % (GRID * 5) == 0
        draw.line([0, y, WIDTH, y], fill="white" if major else (190, 190, 190), width=3 if major else 1)
    for y in range(0, HEIGHT, GRID * 5):
        for x in range(0, WIDTH, GRID * 5):
            draw.text((x + 8, y + 8), f"{x},{y}", fill="black", font=font)
    cx, cy = WIDTH // 2, HEIGHT // 2
    draw.line([cx - 60, cy, cx + 60, cy], fill="red", width=5)
    draw.line([cx, cy - 60, cx, cy + 60], fill="red", width=5)
    img.save(path)


def main() -> None:
    if len(sys.argv) != 2:
        sys.exit(__doc__)
    out = Path(sys.argv[1])
    out.mkdir(parents=True, exist_ok=True)
    checker(out / "checker_360.png")
    grid(out / "grid_numbered.png")


if __name__ == "__main__":
    main()
